export class Book{
    bookId: number=0;
    bookName: String="";
    author: String="";
    genre: String="";
    price: number=0;
    isBookBorrowed: Boolean=false;
    status:String=""

    constructor(bookId:number,bookName:String,author:String,genre:String,price:number
        ,isBookBorrowed:Boolean,status:String){}
}