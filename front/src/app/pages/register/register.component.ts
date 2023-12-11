import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    email: any;
    password: any;
    username: any;

  constructor() { }

  ngOnInit(): void {
  }

    register() {

    }
}
