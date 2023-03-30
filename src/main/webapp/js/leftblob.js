/**
 * 
 */
 /** 
 * @author Stallion-X
 */
 $(window).on("load",function() {
  var height = window.innerHeight,
  x = 10,y = height / 2,
  curveX = 10,
  curveY = 0,
  targetX = 0,
  xitteration = 0,
  yitteration = 0,
  menuExpanded = false;

  blob = $('#blob'),
  blobPath = $('#blob-path'),
  
  $(this).on('mousemove', function (e) {
    x = e.pageX-137;
    if (x<127) {
    	x=-1;
    }
    y = e.pageY-89;
    y -= (getScrollTop() + 2);
    if (y<89) {
    	y = 89;
    }
  });

  function easeOutExpo(currentIteration, startValue, changeInValue, totalIterations) {
    return changeInValue * (-Math.pow(2, -10 * currentIteration / totalIterations) + 1) + startValue;
  }

  var hoverZone = 140;
  var expandAmount = 20;

  function svgCurve() {
    if (curveX > x - 1 && curveX < x + 1) {
      xitteration = 0;
    } else {
      if (menuExpanded) {
        targetX = 0;
      } else {
        xitteration = 0;
        if (x > hoverZone) {
          targetX = 0;
        } else {
          targetX = -((60 + expandAmount) / 100 * (x - hoverZone));
        }
      }
      xitteration++;
    }
    if (curveY > y - 1 && curveY < y + 1) {
      yitteration = 0;
    } else {
      yitteration = 0;
      yitteration++;
    }
    curveX = easeOutExpo(xitteration, curveX, targetX - curveX, 100);
    curveY = easeOutExpo(yitteration, curveY, y - curveY, 100);
    var anchorDistance = 200;
    var curviness = anchorDistance - 40;
    var newCurve2 = "M60," + height + "H0V0h60v" + (curveY - anchorDistance) + "c0," + curviness + "," + curveX + "," + curviness + "," + curveX + "," + anchorDistance + "S60," + curveY + ",60," + (curveY + anchorDistance * 2) + "V" + height + "z";
    blobPath.attr('d', newCurve2);
    blob.width(curveX + 60);
    window.requestAnimationFrame(svgCurve);
  }

  window.requestAnimationFrame(svgCurve);
  $('#blob').css('visibility','visible');

});