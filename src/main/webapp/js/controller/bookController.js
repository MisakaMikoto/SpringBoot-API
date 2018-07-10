app.controller('bookController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {

    var book = new Book();

    setUserName();

    $scope.search = () => {
        let searchPromise = book.searchKakaoAPI($scope.query, $cookies.get('memberId'));
        searchPromise.then((response) => {
            if(response instanceof Array) {
                $scope.books = response;
                $scope.$apply();

            } else {
                alert('session disconnected. return login page');
                logout();
            }

        }, (error) => {
            console.error('Failed!', error);
        });
    };

    $scope.showDetail = (index) => {
        let detailBook = book.showDetail(index);
        console.log(detailBook);
    };

    $scope.goHistories = () => {
        $location.path('/history');
    };

    $scope.logout = () => {
        logout();
    };

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        $location.path('/');
        $scope.$apply();
    }

    function setUserName() {
        $('#memberName').html($cookies.get('memberName'));
    }
}]);