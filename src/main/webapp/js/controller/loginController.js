app.controller('loginController', ['$scope', '$location', function ($scope, $location) {
    var login = new Login();
    login.triggerEnter();

    $scope.login = () => {
        let loginPromise = login.findUser($scope.id, $scope.password);
        loginPromise.then((response) => {
            if(response.status) {
                alert(response.message + ' welcome ' + response.userName);
                $location.path('/book');
                $scope.$apply();

            } else {
                alert(response.message);
            }
        },(error) => {
            console.error('Failed!', error);
        });
    };

    $scope.signin = () => {
        $location.path('/signin');
    };
}]);