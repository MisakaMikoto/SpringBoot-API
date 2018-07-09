class Book {
    constructor() {
        this._booksJSON = '';
    }

    set booksJSON(booksJSON) {
        this._booksJSON = booksJSON;
    }

    get booksJSON() {
        return this._booksJSON;
    }

    triggerEnter() {
        $('#query').keydown((key) => {
            if (key.keyCode == 13) {
                $('#search').trigger('click');
            }
        });
    }

    searchKakaoAPI(query) {
        let sendQuery = query;
        let commonPromise = new CommonPromise();

        return commonPromise.get('/books?query=' + sendQuery).then((response) => {
            let booksJSON = JSON.parse(response);
            this.booksJSON = booksJSON;

            return JSON.parse(response);

        }, function(error) {
            console.error('Failed!', error);
        });
    }

    showDetail(tr) {
        let index = $('tr', $(tr).closest("table")).index(tr);
    }
}