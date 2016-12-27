$(function() {

    var currentIndex = 1;

    //asideBar slide effect
    $('.navigation li').bind('mouseover', function(event) {
        var node = event.target;
        if (node.nodeName !== 'A') {
            node = $(node).parent('a');
        }
        var index = $(node).data('target').split('_')[1];
        var $top = Number($('.mouse').css('top').split('px')[0]);
        $top = (index - 6) * 52;
        $('.mouse').css('top', $top + 'px');
        // if (index > 1 && currentIndex == 1) {
        //     $('.scroll:nth-of-type(1)').slideUp(1500);
        //     currentIndex = 2;
        // } else if (index <= 1 && currentIndex == 2) {
        //     $('.scroll:nth-of-type(1)').slideDown(1800);
        //     currentIndex = 1;
        // }

        if (index > currentIndex) {
            for (var i = currentIndex; i < index; i++) {
                console.log(i)
                $('.scroll:nth-of-type(' + i + ')').slideUp(800);
            }

        } else if (index < currentIndex) {
            for (var i = index; i < currentIndex; i++) {
                $('.scroll:nth-of-type(' + i + ')').slideDown(800);
            }
        }

        currentIndex = index;
    });
    //asideBar slide effect end

});
