app.controller("signinController", ['$scope', '$location', '$http', '$rootScope', function ($scope, $location, $http, $rootScope) {
    $scope.create = function () {
        let params = {
            id: $scope.id,
            password: $scope.password,
            name: $scope.name
        };

        let commonPromise = new CommonPromise();
        commonPromise.post('/account/signin', params).then(function(response) {
            let result = JSON.parse(response);
            alert(result.message);

            if(result.status) {
                $location.path("/book");
                $rootScope.$apply();
            }
        }, function(error) {
            console.error("Failed!", error);
        });
    };

    function redirect() {

    }
}]);