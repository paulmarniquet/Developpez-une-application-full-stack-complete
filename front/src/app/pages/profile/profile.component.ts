import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {User} from "../../interfaces/user.interface";
import {Topic} from "../../interfaces/topic.interface";
import {Router} from "@angular/router";

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

    constructor(private profileService: ProfileService, private router: Router) {
    }

    ngOnInit(): void {
        this.profileService.getProfile().subscribe((user: any) => {
            this.username = user.name
            this.email = user.email
            this.topics = user.topics
        });
    }

    save() {
        this.profileService.modifyProfile({
            name: this.username,
            email: this.email,
        }).subscribe(() => {
        });
    }

    logOut() {
        localStorage.removeItem('token');
        this.router.navigate(['/login']);
    }
}
