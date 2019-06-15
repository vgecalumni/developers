$(function() {
    $.scrollify({
        section : "section",
        easing: "swing",
        scrollSpeed: 1100,
        offset : 0,
        scrollbars: true,
        standardScrollElements: "",
        setHeights: true,
        overflowScroll: true,
        updateHash: true,
        touchScroll:true,
        before:function() {},
        after:function() {},
        afterResize:function() {},
        afterRender:function() {}
    });
          
    $('.carousel').carousel({
        interval: 2000
    });
          
    $("#nav_link a").click(function(e) {
            e.preventDefault();
            var href = $(this).attr("href");
            animateCSS('#homepage', 'bounceOutRight');
            window.location = href;
    });
    
    function animateCSS(element, animationName, callback) {
        const node = document.querySelector(element)
        node.classList.add('animated', animationName)

        function handleAnimationEnd() {
            node.classList.remove('animated', animationName)
            node.removeEventListener('animationend', handleAnimationEnd)
            if (typeof callback === 'function') callback()
        }

        node.addEventListener('animationend', handleAnimationEnd)
    }
    var text = $(".text");
    
    setTimeout(function() {
            
        text.removeClass("text-animate-hide");
             
    }, 1000);
    $("#owl-home").owlCarousel({
      autoPlay: 6000,
      items : 4,
      itemsDesktop : [1199,4],
      itemsDesktopSmall : [979,4],
      itemsTablet: [768,2],
      itemsTabletSmall: [985,2],
      itemsMobile : [479,1],
    });
    $("#owl-highlights").owlCarousel({
      autoPlay: 6000,
      items : 3,
      itemsDesktop : [1199,3],
      itemsDesktopSmall : [979,3],
      itemsTablet: [768,2],
      itemsTabletSmall: [985,3],
      itemsMobile : [479,1],
    });
    $("#owl-aboutus").owlCarousel({
      autoPlay: 6000,
      items : 3,
      itemsDesktop : [1199,3],
      itemsDesktopSmall : [979,3],
      itemsTablet: [768,2],
      itemsTabletSmall: [985,3],
      itemsMobile : [479,1],
    });
    
    $("#owl-things").owlCarousel({
      autoPlay: 6000,
      items : 6,
      itemsDesktop : [1199,6],
      itemsDesktopSmall : [979,4],
      itemsTablet: [768,3],
      itemsTabletSmall: [985,4],
      itemsMobile : [479,2],
    });
});