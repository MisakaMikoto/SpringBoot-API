app.controller('bookController', ['$scope', '$location', '$cookies', '$uibModal', function ($scope, $location, $cookies, $uibModal) {

    if($cookies.get('memberId') === undefined) {
        disconnect();

    } else {
        setUserName();

        var pagination = new Pagination();
        var book = new Book();

        $scope.search = () => {
            searchForKakao(1, false);
        };

        $scope.showDetail = (index) => {
            $scope.detail = book.showDetail(index);
            createModal();
        };

        $scope.goHistories = () => {
            $location.path('/history');
        };

        $scope.goBookmarks = () => {
            $location.path('/bookmark');
        };

        $scope.logout = () => {
            logout();
        };
    }

    function setUserName() {
        let memberName = $cookies.get('memberName') + " 님 ";
        $('#memberName').html(memberName);
    }

    function createModal() {
        $uibModal.open({
            templateUrl: '/component/detail.html',
            scope: $scope,
            backdrop: true,
            controller: function ($scope, $uibModalInstance) {
                $scope.ok = function () {
                    $uibModalInstance.close();
                };
            }
        });
    }

    function searchForKakao(pageIndex, isPageCall) {
        $scope.books = [];

        if(book.validSearch()) {
            let searchPromise = book.searchKakaoAPI($scope.query, pageIndex, $cookies.get('memberId'));
            searchPromise.then((response) => {
                if (response.books !== undefined) {
                    $('.table').show();
                    $scope.books = response.books;
                    $scope.$apply();

                    if(!isPageCall) {
                        $('#pagination').empty();
                        createPagination(response.pagination);
                    }

                } else {
                    disconnect();
                    $scope.$apply();
                }

            }, (error) => {
                console.error('Failed!', error);
            });
        }
    }

    function createPagination(responsePagination) {
        pagination.lastPageNumber = Number(responsePagination.pageableCount / Number($("#size option:selected").val()));
        pagination.pageCount = Number(responsePagination.pageCount);
        pagination.createPage(searchPage);
    }

    function searchPage (pageIndex) {
        searchForKakao(pageIndex, true);
    }

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        alert('로그인 페이지로 돌아갑니다.');
        $location.path('/');
    }

    function disconnect() {
        if($cookies.get('memberId') !== undefined && $cookies.get('memberName') !== undefined) {
            $cookies.remove('memberId');
            $cookies.remove('memberName');
        }
        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
        $location.path('/');
    }
}]);