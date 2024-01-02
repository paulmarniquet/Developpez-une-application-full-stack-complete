import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
    constructor(private router: Router, private matSnackBar: MatSnackBar) {}

    isLogged : boolean = localStorage.getItem('token') != null;

    ngOnInit(): void {}

    login() {
        this.router.navigate(['/login']);
    }

    register() {
        this.router.navigate(['/register']);
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('userID');
        this.router.navigate(['/login']);
        this.matSnackBar.open('You are logged out', 'Close', {
            duration: 2000,
        });
    }
}
