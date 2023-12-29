$(document).ready(function(){
    loadBooks();
});

function loadBooks() {
    $.getJSON("./books").done(function(books){
        updateBooksView(books);
    }).fail(function(e){
        console.log(e);
    });
}



function updateBooksView(books) {
    $("#booksView").empty();
    for (var book of books){
       var view = $(`
       <div class="col-4">
           <a href="#" data-toggle="tooltip" title="Primary">
             <div class="color-entry bg-secondary">
               <img class="color-entry bg-secondary" src=""/>
               <span class="color-code z-depth-2 font-weight-bold text-uppercase rounded name" style="bottom:130px;">
               </span>
               <span class="color-code z-depth-2 font-weight-bold text-uppercase rounded author" style="bottom:80px;">
               </span>
               <span class="color-code z-depth-2 font-weight-bold text-uppercase rounded price">
               </span>
             </div>
             <div class="color-entry bg-secondary">
<!--                <p class="heading heading-5" style="color:#495057;margin-left: 15px;margin-right: 15px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>-->
                <span class="color-code z-depth-2 font-weight-bold text-uppercase rounded content" style="top:10px;left: 10px;bottom:10px;right: 10px"></span>
             </div>
           </a>
       </div>
    `);
       view.find("img").attr("src", "./image/upload/" + book.picture);
       view.find("span.name").append(book.name);
       view.find("span.author").append("作者：" + book.author);
       view.find("span.price").append("价格：" + book.price);
       view.find("span.content").append(book.content);
       $("#booksView").append(view);
    }
}


function updateBooksView3(books) {
    $("#booksView").empty();
    for (var book of books) {
        var view = $(`
            <div class="col-md-4">
               <div class="card">
                   <div class="card-body pb-5">
                       <div class="pt-4 pb-5">
                           <img src="" class="img-fluid img-center" style="height: 150px;" alt="Illustration" />
                       </div>
                       <h5 class="h4 lh-130 mb-3"></h5>
                       <p class="text-muted mb-0 price"></p>
                       <p class="text-muted mb-0 content"></p>
                   </div>
               </div>
            </div>
        `);
        view.find("img").attr("src", "./image/upload/" + book.picture);
        view.find("h5").append(book.name);
        view.find("p.price").append("作者：" + book.author + "， 价格：" + book.price);
        view.find("p.content").append(book.content);
        $("#booksView").append(view);
    }
}

function updateBooksView2(books) {
    var table = $("<table class='tb-book'></table>");
    for (var book of books) {
        var tr = $("<tr></tr>");
        tr.append("<td>" + book.name + "</td>");
        tr.append("<td>" + book.author + "</td>");
        tr.append("<td>" + book.price + "</td>");
        tr.append("<td>" + book.content + "</td>");
        tr.append("<td><img src='./image/upload/" + book.picture + "'/></td>");
        table.append(tr);
    }
    $("#booksView").empty().append(table);
}


