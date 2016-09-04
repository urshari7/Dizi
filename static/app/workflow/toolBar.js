var app = angular.module('DAppModule');

function placeNode(node, top, left) {
    node.css({
      position: "absolute",
      top: top + "px",
      left: left + "px",
    });
}

  // To create a empty resizable and draggable box
  app.directive("ceBoxCreator", function($document, $compile) {
    return {
      restrict: 'A',
      link: function($scope, $element, $attrs) {
        $element.on("click", function($event) {
          var newNode;
          if($element.is(":checkbox")) {
              $element.attr('checked', false);
              newNode = $compile('<input type="checkbox" ce-drag ce-resize></input>')($scope);
          }else{
              newNode = $compile('<div class="contentEditorBox" ce-drag ce-resize></div>')($scope);
          }
          placeNode(newNode, $event.pageY - 25, $event.pageX - 25);
          angular.element($document[0].body).append(newNode);
        });
      }
    }
  });


  // To manage the drag
  app.directive("ceDrag", function($document) {
    return function($scope, $element, $attr) {
      var startX = 0,
          startY = 0;
      
      var newElement = $element; 
      //var newElement = angular.element('<div class="draggable"></div>');
      
      //$element.append(newElement);
    
        
      
      newElement.on("mousedown", function($event) {
        
        event.preventDefault();
        
        // To keep the last selected box in front
        angular.element(document.querySelectorAll(".contentEditorBox")).css("z-index", "0");
        //angular.element(document.querySelectorAll("toolCheck")).css("z-index", "0");          
          
        $element.css("z-index", "1");

        startX = $event.pageX - $element[0].offsetLeft;
        startY = $event.pageY - $element[0].offsetTop;
        //console.log('p:'+$element[0].offsetParent);  
        //console.log('x:'+$element[0].offsetLeft);
        //console.log('y:'+$element[0].offsetTop);
        var test = $('#pdfCanvas');
          console.log('1:'+test);
        var top = test.offset().top;
          console.log('2:'+top);
        var pdfOff = $('#pdfCanvas').offset();
        console.log($('#pdfCanvas'));
        //console.log($('#pdfCanvas')[0].position());
        console.log($('#pdfCanvas').offset());
        console.log('x:'+pdfOff.left);
        console.log('y:'+pdfOff.top);
        //console.log($('#pdfCanvas')[0].pageY);  
        
        $document.on("mousemove", mousemove);
        $document.on("mouseup", mouseup);
      });

      function mousemove($event) {
        placeNode( $element , $event.pageY - startY , $event.pageX - startX );
        //placeNode( $element , $event.pageY , $event.pageX);
      }

      function mouseup() {
        $document.off("mousemove", mousemove);
        $document.off("mouseup", mouseup);
      }
    };
  });

  // To manage the resizers
  app.directive("ceResize", function($document) {
    return function($scope, $element, $attr) {
      //Reference to the original 
      var $mouseDown; 
      
      // Function to manage resize up event
      var resizeUp = function($event) {
        var margin = 20,
            lowest = $mouseDown.top + $mouseDown.height - margin,
            top = $event.pageY > lowest ? lowest : $event.pageY,
            height = $mouseDown.top - top + $mouseDown.height;

        $element.css({
          top: top + "px",
          height: height + "px"
        });
      };

      // Function to manage resize right event
      var resizeRight = function($event) {
        var margin = 20,
            leftest = $element[0].offsetLeft + margin,
            width = $event.pageX > leftest ? $event.pageX - $element[0].offsetLeft : margin;
        
        $element.css({
          width: width + "px"
        });
      };

      // Function to manage resize down event
      var resizeDown = function($event) {
        var margin = 20,
            uppest = $element[0].offsetTop + margin,
            height = $event.pageY > uppest ? $event.pageY - $element[0].offsetTop : margin;

        $element.css({
          height: height + "px"
        });
      };

      // Function to manage resize left event
      function resizeLeft ($event) {
        var margin = 20,
            rightest = $mouseDown.left + $mouseDown.width - margin,
            left = $event.pageX > rightest ? rightest : $event.pageX,
            width = $mouseDown.left - left + $mouseDown.width;        

        $element.css({
          left: left + "px",
          width: width + "px"
        });
      };

     var createResizer = function createResizer( className , handlers ){
        
        //var newElement = angular.element( '<div class="' + className + '"></div>' );
        //$element.append(newElement);
        var newElement = $element;
        $element.addClass(className);
        //$element.append('Hi<br/>');
        console.log($element);
        
        newElement.on("mousedown", function($event) {
        
          $document.on("mousemove", mousemove);
          $document.on("mouseup", mouseup);
          console.log('clicc');
          //Keep the original event around for up / left resizing
          $mouseDown = $event;
          $mouseDown.top = $element[0].offsetTop;
          $mouseDown.left = $element[0].offsetLeft
          $mouseDown.width = $element[0].offsetWidth;
          $mouseDown.height = $element[0].offsetHeight;                

          function mousemove($event) {
            event.preventDefault();
            for( var i = 0 ; i < handlers.length ; i++){
              handlers[i]( $event );
            }
          }

          function mouseup() {
            $document.off("mousemove", mousemove);
            $document.off("mouseup", mouseup);
          }         
        });
      }
      
     
      //createResizer( 'sw-resize' , [ resizeDown , resizeLeft ] );
      //createResizer( 'ne-resize' , [ resizeUp   , resizeRight ] );
      //createResizer( 'nw-resize' , [ resizeUp   , resizeLeft ] );
      //createResizer( 'se-resize' , [ resizeDown ,  resizeRight ] );
      //createResizer( 'w-resize' , [ resizeLeft ] );
      //createResizer( 'e-resize' , [ resizeRight ] );
      createResizer( 'n-resize' , [ resizeUp ] );
      //createResizer( 's-resize' , [ resizeDown ] );
    };

  });
