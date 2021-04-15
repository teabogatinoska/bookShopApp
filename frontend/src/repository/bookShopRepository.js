import axios from '../custom-axios/axios';

const BookShopService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    markBook: (id) => {
        return axios.get(`/books/markAsTaken/${id}`);
    },
    addBook: (name,category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    editBook: (id, name,copies, category,author) => {
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "copies" : copies,
            "category" : category,
            "author" : author
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
}

export default BookShopService;
