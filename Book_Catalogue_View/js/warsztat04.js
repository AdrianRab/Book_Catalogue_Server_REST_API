$(document).ready(function(){
  var allBooks = $('#allBooks');

  allBooksList();
  addBook();


  function allBooksList() {
    $.ajax({
  				url:	'http://localhost:8090/books/allBooks',
          data: {},
          type:"GET",
          dataType: "json",
        })
  				.done(function(json){
            var	newBook	=	$('<li>');
            newBook.addClass('list-group-item'); //stylowanie boostrapem
            for (var i = 0; i < json.length; i++) {
              var title = $('<div>').text(json[i].id + '. '+ json[i].title);
              title.addClass('list-group-item-action book-title');

              var emptyDiv= $('<div>');
              emptyDiv.addClass('empty-div').hide();
              emptyDiv.attr("hidden");

              var buttons = $('<div>');
              buttons.addClass('book-buttons');

              var bookId = $('<p>').text(json[i].id);
              bookId.addClass("book-id").hide();

              var author = $('<p>').text('Author: ' +json[i].author);
              author.addClass('list-group-item-action  book-author');

              var publisher = $('<p>').text('Publisher: ' + json[i].publisher);
              publisher.addClass('list-group-item-action book-publisher');

              var type = $('<p>').text('Genre: ' +json[i].type);
              type.addClass('list-group-item-action book-type');

              var isbn = $('<p>').text('ISBN number: ' +json[i].isbn);
              isbn.addClass('list-group-item-action book-isbn');

              var space = $('<br>');

              var showDetailsBtn =$('<button>').text("Show details");
              showDetailsBtn.addClass('button is-primary show');

              var deleteBook = $('<button>').text('Delete ' + json[i].title);
              deleteBook.addClass('button is-danger delete-button');

              var editBook = $('<button>').text('Edit ' + json[i].title);
              editBook.addClass('button is-info edit-button');

              emptyDiv.append(space, author, publisher, type, isbn, space);
              buttons.append(showDetailsBtn, space, deleteBook, space, editBook)
              title.append(space, emptyDiv, space, buttons, space, space);
              newBook.append(title);
              allBooks.append(newBook);
            }
            showBookDetails();
            removeBook();
          })
  				.fail(function(xhr,	status,
  				      errorThrown){
                   console.log("The following error occurred: "+ status, errorThrown);
                	})
  				.always(function(xhr,	status	){
            console.log("Title upload - finished");
              });
        }

    function showBookDetails() {
      var showButton = $('.show');
      showButton.on('click', function(){
      $(this).parent().siblings('div').toggle(500);
          });
      };

    function addBook(){
        var form = $('#add-book');
        var book = {
            	title: "",
            	author: "",
            	publisher: "",
              type: "",
              isbn: ""};

        form.submit(function(event) {
          event.preventDefault();

          book.title = $('#newTitle').val();
          book.author = $('#newAuthor').val();
          book.publisher = $('#newPublisher').val();
          book.type = $('#newGenre').val();
          book.isbn = $('#newIsbn').val();

          console.log($('#newTitle').val());

        $.ajax({
            headers: {'Accept': 'application/json',
            'Content-Type': 'application/json'},
            type: 'POST',
            data: JSON.stringify(book),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8090/books/",

        })
          .done(function (response, textStatus, jqXHR){
              console.log("Book successfully added.");
              $('#confirmation').children().remove();
              $('.list-group-item').remove();
              var confirmationMessage = $('<h2>').text('Book has been succesfully added');
              var confirmationDiv =$('#confirmation');
              confirmationDiv.css({"color": "green", "font-size": "200%", "align": "center"});
              confirmationDiv.append(confirmationMessage);
              $('#newTitle').val("");
              $('#newAuthor').val("");
              $('#newPublisher').val("");
              $('#newGenre').val("");
              $('#newIsbn').val("");
              allBooksList();
          })
          .fail(function (xhr, status, errorThrown){
              console.error("The following error occurred: "+ status, errorThrown);
              $('#confirmation').children().remove();
              var confirmationMessage = $('<h2>').text('Error. Book has not been added');
              var confirmationDiv =$('#confirmation');
              confirmationDiv.css({"color": "red", "font-size": "200%", "text-align": "center"});
              confirmationDiv.append(confirmationMessage);
          })
          .always(function () {
            $('#deleteConfirmation').children().remove();
            console.log("Add book function ended");
          });
        });
    };

    console.log($('.delete-button'));
    console.log($('.show'));

   function removeBook() {
     var deleteButton = $('.delete-button');
     deleteButton.on('click', function(event) {
       var bookToDelete = $(this).parent().parent().text();
       var id = bookToDelete.substring(0,bookToDelete.indexOf('.'));
       $.ajax({
         url: "http://localhost:8090/books/deleteBook/"+ id,
         type: 'DELETE',
       })
       .done(function (response, textStatus, jqXHR){
         console.log("Book successfully deleted");
         $('#deleteConfirmation').children().remove();
         $('.list-group-item').remove();
         var confirmationMessage = $('<h2>').text('Book has been succesfully deleted');
         var confirmationDiv =$('#deleteConfirmation');
         confirmationDiv.css({"color": "green", "font-size": "200%", "align": "center"});
         confirmationDiv.append(confirmationMessage);
         allBooksList();
       })
       .fail(function (xhr, status, errorThrown){
           console.error("The following error occurred: "+ status, errorThrown);
           $('#deleteConfirmation').children().remove();
           var confirmationMessage = $('<h2>').text('Error. Book has not been deleted');
           var confirmationDiv =$('#deleteConfirmation');
           confirmationDiv.css({"color": "red", "font-size": "200%", "text-align": "center"});
           confirmationDiv.append(confirmationMessage);
       })
       .always(function () {
         $('#confirmation').children().remove();
         console.log("Remove book function ended");
       });
     });
   };

});
