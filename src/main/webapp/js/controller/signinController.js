app.controller("signinController", ['$scope', '$location', function ($scope, $location) {

    var signin = new Signin();

    $scope.create = () => {
        let params = {
            id: $scope.id,
            password: $scope.password,
            name: $scope.name
        };

        let signinPromise = signin.createAccount(params);
        signinPromise.then((response) => {
            if (response.status) {
                alert("회원가입에 성공하였습니다.");
                $location.path("/");
                $scope.$apply();

            } else {
                alert(response.message);
            }

        }, (error) => {
            console.error('Failed!', error);
        });
    };

    $scope.cancel = () => {
        $location.path("/");
        $scope.$apply();
    };
}]);