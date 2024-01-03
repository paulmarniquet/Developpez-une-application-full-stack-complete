import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {User} from "../../interfaces/user.interface";
import {Topic} from "../../interfaces/topic.interface";
import {Router} from "@angular/router";
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    username: any;
    email: any;
    public user: User | undefined;
    public topics: Topic[] = [];

    constructor(private profileService: ProfileService, private router: Router, private matSnackBar: MatSnackBar) {
    }

    ngOnInit(): void {
        this.profileService.getProfile(Number(localStorage.getItem('userID')!)).subscribe((user: any) => {
            this.username = user.name
            this.email = user.email
            this.topics = user.topics
        });
    }

    save() {
        this.profileService.modifyProfile({
            name: this.username,
            email: this.email,
        }, Number(localStorage.getItem('userID')!)).subscribe(() => {
                this.router.navigate(['/me']);
                this.matSnackBar.open('Profile updated', 'Close', {
                    duration: 2000,
                    panelClass: ['greenToast']
                });

            },
            (error: any) => {
                console.error("Save error:", error.error);
                this.matSnackBar.open('Some fields are invalid, please try again',
                    'Close', {
                        duration: 3000,
                        panelClass: ['redToast']
                    });
            }
        );
    }

    logOut() {
        localStorage.removeItem('token');
        localStorage.removeItem('userID');
        this.router.navigate(['/login']);
        this.matSnackBar.open('You are logged out', 'Close', {
            duration: 2000,
        });
    }
}
