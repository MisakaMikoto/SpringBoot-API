app.controller('searchController', ['$scope', '$location', '$cookies', '$uibModal', function ($scope, $location, $cookies, $uibModal) {

    if($cookies.get('memberId') === undefined) {
        disconnect();
    }

    var pagination = new Pagination();
    var search = new Search();
    var bookMark = new BookMakrs();

    setUserName();

    $scope.search = () => {
        searchForKakao(1, false);
    };

    $scope.showDetail = (index) => {
        $scope.detail = search.showDetail(index);
        createModal();
    };

    $scope.goHistories = () => {
        $location.path('/history');
    };

    $scope.logout = () => {
        logout();
    };

    function setUserName() {
        let memberName = $cookies.get('memberName') + " 님 ";
        $('#memberName').html(memberName);
    }

    function createModal() {
        $uibModal.open({
            templateUrl: '/component/detail.html',
            scope: $scope,
            controller: function ($scope, $uibModalInstance) {
                $scope.ok = function () {
                    $uibModalInstance.close();
                };
            }
        });
    }

    function searchForKakao(pageIndex, isPageCall) {
        $scope.books = [];

        if(search.validSearch()) {
            let searchPromise = search.searchKakaoAPI($scope.query, pageIndex, $cookies.get('memberId'));
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
    };

    function logout() {
        $cookies.remove('memberId');
        $cookies.remove('memberName');
        alert('로그인 페이지로 돌아갑니다.');
        $location.path('/');
        $scope.$apply();
    }

    function disconnect() {
        if($cookies.get('memberId') !== undefined && $cookies.get('memberName') !== undefined) {
            $cookies.remove('memberId');
            $cookies.remove('memberName');
        }
        alert('세션이 종료되었습니다. 로그인 페이지로 돌아갑니다.');
        $location.path('/');
        $scope.$apply();
    }
}]);