app.controller('modifyController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {
    var modify = new Modify();

    $scope.modify = () => {
        if(modify.validateModify()) {
            let params = {
                id: $scope.id,
                password: $scope.password,
                modifyPassword: $scope.modifyPassword
            };
            let loginPromise = modify.checkPassword(params);
            loginPromise.then((response) => {
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