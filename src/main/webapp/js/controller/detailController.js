app.controller('detailController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {
    if(checkCookies()) {
        $location.path('/book');
    }

    function checkCookies() {
        if($cookies.get('memberId') !== undefined) {
            return true;

        } else {
            return false;
        }
    }
}]);