import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../../components/header/header.component';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    user: any;
    password: any;

    constructor() {
    }

    ngOnInit(): void {
    }

    login() {
        console.log("login");
    }

}
