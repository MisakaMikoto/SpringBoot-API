class Book {
    constructor() {
        this._booksJSON = '';
    }

    set booksJSON(booksJSON) {
        this._booksJSON = booksJSON;
    }

    searchKakaoAPI(query, memberId) {
        let sendQuery = query;
        let commonPromise = new CommonPromise();

        return commonPromise.get('/books?query=' + sendQuery + "&memberId=" + memberId).then((response) => {
            let booksJSON = JSON.parse(response);
            this.booksJSON = booksJSON;

            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    showDetail(index) {
        return this._booksJSON[index];
    }
}