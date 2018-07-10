app.controller("signinController", ['$scope', '$location', function ($scope, $location) {
    $scope.create = () => {
        let params = {
            id: $scope.id,
            password: $scope.password,
            name: $scope.name
        };

        let commonPromise = new CommonPromise();
        commonPromise.post('/signin', params).then((response) => {
            let result = JSON.parse(response);
            alert(result.message);

            if(result.status) {
                $location.path("/");
                $scope.$apply();
            }
        }, (error) => {
            console.error("Failed!", error);
        });
    };
}]);