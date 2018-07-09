app.controller('searchHistoryController', ['$scope', '$location', function ($scope, $location) {

    let searchHistory = new SearchHistory();
    let searchHistoryPromise = searchHistory.getList();

    searchHistoryPromise.then((response) => {
        $scope.searchHistories = response;
        $scope.$apply();

    }, (error) => {
        console.error('Failed!', error);
    });
}]);