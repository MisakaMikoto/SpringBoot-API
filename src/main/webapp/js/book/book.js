class Book {
    constructor() {
        this._booksJSON = '';
    }

    set booksJSON(booksJSON) {
        this._booksJSON = booksJSON;
    }

    searchKakaoAPI(query, pageIndex, memberId) {
        let sendQuery = query;
        let sendSort = $("#sort option:selected").val();
        let sendSize = Number($("#size option:selected").val());
        let sendPage = Number(pageIndex);

        let commonRequestPromise = new CommonRequestPromise();
        return commonRequestPromise.get('/v1/book/search/books?query=' + sendQuery + "&page=" + sendPage + "&sort=" + sendSort + "&size=" + sendSize + "&memberId=" + memberId)
            .then((response) => {
                let parseJSON = JSON.parse(response);

                if(parseJSON.status !== undefined) {
                    return parseJSON

                } else {
                    this.booksJSON = parseJSON.books;
                    $('#totalCount').html('총 ' + parseJSON.pagination.pageableCount + ' 건');

                    return parseJSON;
                }

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
        if($('#query').val().trim().length == "") {
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