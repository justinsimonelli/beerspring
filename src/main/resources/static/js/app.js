$(document).ready(function(){
    console.log('ready');

    $("#datepicker").datepicker();

    $( "#beers" ).autocomplete({
      source: function( request, response ) {
        $.ajax({
          url: "https://api.brewerydb.com/v2/search/?key=f455ba07edfba1d9b8261a6166fada13&type=beer&withBreweries=y&p=1",
          //url: "js/beers.json",
          dataType: "json",
          crossDomain:true,
          data: {
            q: request.term
          },
          success: function( data ) {
            response( beerInfo );
          },
          error: function(request, status, error){
            console.log(error);
          }
        });
      },
      minLength: 3,
      focus: function( event, ui ) {
        $( "#beers" ).val( ui.item.name );
        return false;
      },
      select: function( event, ui ) {
        var beer = ui.item,
            list = $("#log > #beer-list");

        console.log( ui.item ?
          "Selected: " + beer.name :
          "Nothing selected, input was " + this.value);

        //use handlebars templates here
        list.append("<li class='beer'><img class='label' src='" + beer.labels.large + "'/><span class='list-beer'>" + beer.name + " - " + beer.abv +"% ABV</span><span class='list-brewery'>By: "+beer.breweries[0].name+"</span></li>")


      },
      open: function() {
        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      },
      close: function() {
        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      }
    }).autocomplete( "instance" )._renderItem = function( ul, item ) {
            var beer = item,
                abv = beer.abv ? beer.abv + "% ABV" : "Unknown ABV??";
                brewery = beer.breweries ? beer.breweries[0].name : "Brewery not available";
            return $( "<li>" )
              .append( "<a>" + beer.name + " - " + abv + "<br>" + brewery +"</a>" )
              .appendTo( ul );
          };;
});