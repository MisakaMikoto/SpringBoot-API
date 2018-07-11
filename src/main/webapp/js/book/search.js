class Search {
    constructor() {
        this._booksJSON = '';
        this._pageList = [];
    }

    set booksJSON(booksJSON) {
        this._booksJSON = booksJSON;
    }

    set pageList(paginationJSON) {
        this._pageList = [];

        let pageCount = Number(paginationJSON.pageableCount / Number($("#size option:selected").val()));
        for(let i = 0; i < pageCount; i++) {
            this._pageList.push(i);
        }
    }

    get pageList() {
        return this._pageList;
    }

    searchKakaoAPI(query, pageIndex, memberId) {
        let sendQuery = query;
        let sendSort = $("#sort option:selected").val();
        let sendSize = Number($("#size option:selected").val());
        let sendPage = Number(pageIndex);

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/books?query=' + sendQuery + "&page=" + sendPage + "&sort=" + sendSort + "&size=" + sendSize + "&memberId=" + memberId)
            .then((response) => {

                let parseJSON = JSON.parse(response);

                let booksJSON = parseJSON.books;
                let paginationJSON = parseJSON.pagination;

                this.booksJSON = booksJSON;
                this.pageList = paginationJSON;

                return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    showDetail(index) {
        let book =  this._booksJSON[index - 1];
        return this.paserDetailToViewData(book);
    }

    paserDetailToViewData(book) {
        let authors = book.authors;
        if (book !== undefined && book.length > 0) {
            book.authors = authors.toString();
        }

        let translators = book.translators;
        if (translators !== undefined && translators.length > 0) {
            book.translators = translators.toString();
        }

        let saleYn = book.saleYn;
        if (saleYn == 'Y') {
            book.saleYn = "판매 가능";

        } else {
            book.saleYn = "판매 불가능";
        }
        return book;
    }

    validSearch() {
        if($('#query').val().length == "") {
            alert("검색어를 입력해 주세요.");
            return false;

        } else if($( "#size option:selected" ).val() == "0") {
            alert("페이지당 검색 개수를 선택해 주세요.");
            return false;

        } else if($( "#sort option:selected" ).val() == "none") {
            alert("검색의 정렬 조건을 선택해 주세요.");
            return false;

        } else {
            return true;
        }
    }
}