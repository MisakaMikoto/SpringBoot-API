app.controller("timetableController", ['$scope', '$location', '$http', function ($scope, $location, $http) {
    $scope.data = $location.search().data;
    $scope.timeTableData = [];
    $scope.sendSubjects = [];
    $scope.id = $location.search().id;

    for (var i = 0; i < $scope.data.length; i++) {
        let sendSchedule = {"userId" : $scope.id, "time" : "", "subject" : ""};
        sendSchedule.time = i;

        let timeSchedule = {"time" : 9 + parseInt((i * 2)) + ": 00~" + parseInt(9 + parseInt(((i + 1) * 2))) + " : 00",
            "monday" : "", "tuesday" : "", "wednesday" : "", "thursday" : "", "friday" : ""};

        let weekData = $scope.data[i];
        for(var j = 0; j < weekData.length; j++) {
            let dayData = "";
            if (weekData[j] != "x") {
                dayData = weekData[j];
            }

            if (j == 0) {
                timeSchedule.monday = dayData;
                sendSchedule.subject = weekData[j];

            } else if (j == 1) {
                timeSchedule.tuesday = dayData;
                sendSchedule.subject += "," + weekData[j];

            } else if (j == 2) {
                timeSchedule.wednesday = dayData;
                sendSchedule.subject += "," + weekData[j];

            } else if (j == 3) {
                timeSchedule.thursday = dayData;
                sendSchedule.subject += "," + weekData[j];

            } else {
                timeSchedule.friday = dayData;
                sendSchedule.subject += "," + weekData[j];
            }
        }

        // 1교시는 두 시간
        // 본 알고리즘은 교시 두번 씩 push
        $scope.timeTableData.push(timeSchedule);
        $scope.timeTableData.push(timeSchedule);

        // 서버로 저장할 데이터 가공
        $scope.sendSubjects.push(sendSchedule);
    }

    $scope.save = function() {
        $http({
            method: 'POST',
            url: '/schedule/save',
            data: ({
                "subjects" : angular.fromJson($scope.sendSubjects)
            }),
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        }).then(function success(response) {
            if (response.data) {
                alert("저장되었습니다.");
            }

        }, function fail(error) {
            console.log(error);
        });

    }

    $scope.clear = function() {
        $http({
            method: 'POST',
            url: '/schedule/clear',
            data: $.param({
                "userId" : $scope.id
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(function success(response) {
            if (response.data) {
                alert("초기화 되었습니다. 과목 생성 화면으로 이동합니다.");
                $location.path("/mypage");
            }

        }, function fail(error) {
            console.log(error);
        });
    }

    $scope.back = function() {
        $location.path("/mypage");
    }
}]);