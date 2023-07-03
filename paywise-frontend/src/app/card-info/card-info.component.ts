import { Component, OnInit } from '@angular/core';
// import './cardScript.component.js'

declare const cardScript: any;

@Component({
  selector: 'app-card-info',
  templateUrl: './card-info.component.html',
  styleUrls: ['./card-info.component.css', './card-info.component.scss']
})
export class CardInfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    const script = document.createElement('script');
    script.src = './card-info.component.js';
    script.async = true;
    script.onload = () => {
      // The JavaScript file has loaded, you can now use its functionality
      cardScript();
    };
    document.body.appendChild(script);
  }

}
