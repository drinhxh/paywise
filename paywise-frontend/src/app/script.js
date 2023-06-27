let text = document.getElementById('text');
let hill = document.getElementById('hill');
let trees = document.getElementById('trees');
let trees2 = document.getElementById('trees2');


window.addEventListener('scroll', () => {
  let value = window.scrollY;

  text.style.marginTop = value * 2.5 + 'px';
  hill.style.top = value *  -1.5 + 'px';
  hill.style.left = value * 1.5 + 'px';
  hill.style.left = value  * 1.5 + 'px';
  trees2.style.left = value * -1.5 + 'px';
 });


