app.controller('searchHistoryController', ['$scope', '$location', '$cookies', function ($scope, $location, $cookies) {

    let searchHistory = new SearchHistory();
    let searchHistoryPromise = searchHistory.getList($cookies.get('memberId'));

    searchHistoryPromise.then((response) => {
        if(response instanceof Array) {
            $scope.books = response;
            $scope.$apply();

        } else {
            alert('session disconnected. return login page');
            logout();
        }

        $scope.searchHistories = response;
        $scope.$apply();

    }, (error) => {
        console.error('Failed!', error);
    });

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        $location.path('/');
        $scope.$apply();
    }
}]);