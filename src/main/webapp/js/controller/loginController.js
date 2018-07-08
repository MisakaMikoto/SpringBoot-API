app.controller("loginController", ['$scope', '$location', '$http', function ($scope, $location, $http) {
    $scope.login = function () {
        findUser();
    };

    $scope.signin = function () {
        $location.path("/signin");
    };

    function findUser() {
        let params = {
            id: $scope.id,
            password: $scope.password
        };

        let commonPromise = new CommonPromise();
        commonPromise.post('/account/login', params).then((response) => {
            console.log("Success!", response);
        }, function(error) {
            console.error("Failed!", error);
        });
    }

    function createUser() {
        $http({
            method: 'POST',
            url: '/login/create',
            data: $.param({
                id: $scope.id,
                password: $scope.password
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(function success(response) {
            if (response.data) {
                alert("입력된 정보로 새 계정이 생성되었습니다.");
                $location.path("/mypage").search({id: $scope.id});
            }

        }, function fail(error) {
            console.log(error);
        });
    }

    function login() {
        $http({
            method: 'POST',
            url: '/login',
            data: $.param({
                id: $scope.id,
                password: $scope.password
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(function success(response) {
            if (response.data) {
                getSchedule();

            } else {
                alert("패스워드를 확인해 주세요.");
            }

        }, function fail(error) {
            console.log(error);
        });
    }

    function getSchedule() {
        $http({
            method: 'POST',
            url: '/schedule/get',
            data: $.param({
                userId: $scope.id
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(function success(response) {
            if (response.data.length > 0) {
                $location.path("/timetable").search({id: $scope.id, data: response.data});

            } else {
                $location.path("/mypage").search({id: $scope.id});
            }

        }, function fail(error) {
            console.log(error);
        });
    }
}]);