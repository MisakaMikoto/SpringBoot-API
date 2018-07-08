app.controller("mypageController", ['$scope', '$location', '$http', function ($scope, $location, $http) {
    $scope.subjects = [];
    $scope.sendSubjects = [];

    $scope.id = $location.search().id;

    $scope.insert = function () {
        let lastAllCredit = getLastAllCredit();

        let name = $scope.name;
        let credit = $scope.credit;
        let allCredit = parseInt(lastAllCredit) + parseInt($scope.credit);

        if(validateInputCredit(credit) && validateInputName(name) && validateCreditOverflow(allCredit)) {
            $scope.subjects.push(
                {"name": name, "credit": credit, "allCredit": allCredit}
            );

            $scope.sendSubjects.push(
                {"name": name, "credit": credit}
            );
        }
    }

    $scope.delete = function(index) {
        let subject = $scope.subjects[index];
        let targetCredit = $scope.subjects[index].credit;

        for(var i = 0; i < $scope.subjects.length; i++) {
            if(i > index) {
                $scope.subjects[i].allCredit = parseInt($scope.subjects[i].allCredit) - parseInt(targetCredit);
            }
        }
        $scope.subjects.splice($scope.subjects.indexOf(subject), 1);
    }

    $scope.complete = function() {
        if(validateComplete()) {
            $http({
                method: 'POST',
                url: '/schedule/createTimeTable',
                data: {
                    subjects: angular.fromJson($scope.sendSubjects)
                },
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).then(function success(response) {
                if (response.data) {
                    $location.path("/timetable").search({id:$scope.id, data: response.data});
                }

            }, function fail(error) {
                console.log(error);
            });
        }
    }

    function getLastAllCredit() {
        let lastAllCredit = 0;

        let table = angular.element(document).find('table')[0];
        let trs = table.querySelectorAll('tr');

        if(trs.length > 1) {
            let tds = trs[trs.length - 1].querySelectorAll('td');
            lastAllCredit = tds[tds.length - 2].attributes['value'].value;
        }
        return lastAllCredit;
    }

    function validateInputCredit(credit) {
        let isOK = true;
        if(typeof credit == 'undefined') {
            isOK = false;
            alert("신청 학점의 범위는 1 - 3 학점입니다.");
        }
        return isOK;
    }

    function validateInputName(name) {
        let isOK = true;
        for(var i = 0; i < $scope.subjects.length; i++) {
            if($scope.subjects[i].name == name) {
                isOK = false;
                alert("동일한 수강 과목은 등록할 수 없습니다.");
                break;
            }
        }
        return isOK;
    }

    function validateCreditOverflow(allCredit) {
        let isOK = true;

        if(allCredit > 21) {
            isOK = false;
            alert("수강 학점은 21학점을 넘을 수 없습니다.");
        }
        return isOK;
    }

    function validateComplete() {
        let isOK = true;
        let lastAllCredit = getLastAllCredit();

        if(lastAllCredit < 18) {
            isOK = false;
            alert("수강 학점은 최소 18 학점이어야 합니다.");
        }
        return isOK;
    }
}]);