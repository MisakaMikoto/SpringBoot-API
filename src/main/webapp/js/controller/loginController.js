app.controller('loginController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {
    var login = new Login();

    if(checkCookies()) {
        $location.path('/book');
    }

    $scope.login = () => {
        let loginPromise = login.findUser($scope.id, $scope.password);
        loginPromise.then((response) => {
            if(response.status) {
                $cookies.put('memberId', response.memberId);
                $cookies.put('memberName', response.memberName);
                alert(response.message + ' welcome ' + response.memberName);
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

    function checkCookies() {
        if($cookies.get('memberId') !== undefined) {
            return true;

        } else {
            return false;
        }
    }
}]);