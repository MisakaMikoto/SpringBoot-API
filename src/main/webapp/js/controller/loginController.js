app.controller('loginController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {
    var login = new Login();

    if(checkCookies()) {
        $location.path('/search');
    }

    $scope.login = () => {
        if(login.validLogin()) {
            let loginPromise = login.findUser($scope.id, $scope.password);
            loginPromise.then((response) => {
                if (response.status) {
                    $cookies.put('memberId', response.memberId);
                    $cookies.put('memberName', response.memberName);
                    alert(response.message + ' 환영합니다. ' + response.memberName + ' 님');
                    $location.path('/search');
                    $scope.$apply();

                } else {
                    alert(response.message);
                }
            }, (error) => {
                console.error('Failed!', error);
            });
        }
    };

    $scope.signin = () => {
        $location.path('/signin');
    };

    $scope.modify = () => {
        $location.path('/modify');
    };

    function checkCookies() {
        if($cookies.get('memberId') !== undefined) {
            return true;

        } else {
            return false;
        }
    }
}]);