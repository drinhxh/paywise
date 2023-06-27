import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements AfterViewInit {

  constructor() { }

  ngAfterViewInit() {
    const script = document.createElement('script');
    script.src = 'assets/particles.js'; // Replace with the correct path if necessary
    document.body.appendChild(script);
  }

  ngOnInit(): void {
    // Other initialization code if needed
  }
}




// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent implements OnInit {

//   constructor() { }

//   ngOnInit(): void {
//   }

// }
