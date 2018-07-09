app.controller('bookController', ['$scope', '$location', function ($scope, $location) {
    var book = new Book();
    book.triggerEnter();

    $scope.search = () => {
        let searchPromise = book.searchKakaoAPI($scope.query);
        searchPromise.then((response) => {
            $scope.books = response;
            $scope.$apply();

        }, (error) => {
            console.error('Failed!', error);
        });
    };

    $scope.showDetail = (index) => {
        book.showDetail(index);
    };

    $scope.goHistories = () => {
        $location.path('/history');
    };

}]);