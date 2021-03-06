app.controller("signinController", ['$scope', '$location', function ($scope, $location) {

    var signin = new Signin();

    $scope.create = () => {
        if(signin.validSignin()) {
            let params = {
                id: $scope.id,
                password: $scope.password,
                name: $scope.name
            };

            let signinPromise = signin.createAccount(params);
            signinPromise.then((response) => {
                if (response.status) {
                    alert(response.message);
                    $location.path("/");
                    $scope.$apply();

                } else {
                    alert(response.message);
                }

            }, (error) => {
                console.error('Failed!', error);
            });
        }
    };

    $scope.cancel = () => {
        $location.path("/");
    };
}]);