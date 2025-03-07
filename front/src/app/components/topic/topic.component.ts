import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../interfaces/topic.interface";
import {ProfileService} from "../../services/profile.service";
import {User} from "../../interfaces/user.interface";
import {Router} from "@angular/router";

@Component({
    selector: 'app-topic',
    templateUrl: './topic.component.html',
    styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit {

    @Input() topic?: Topic;

    public subscribed!: boolean;

    constructor(private profileService: ProfileService) {}

    ngOnInit(): void {
        this.isSubscribed();
    }

    isSubscribed(): boolean {
        this.profileService.getProfile(Number(localStorage.getItem('userID')!)).subscribe((user: any) => {
            for (let i = 0; i < user.topics.length; i++) {
                if (user.topics[i].id === this.topic?.id) {
                    this.subscribed = true;
                }
            }
        });
        return this.subscribed;
    }

    subscribeTo() {
        this.profileService.subscribeTopic(this.topic!, Number(localStorage.getItem('userID')!)).subscribe(() => {
            this.subscribed = true;
        });
    }

    unsubscribeTo() {
        this.profileService.unsubscribeTopic(this.topic!, Number(localStorage.getItem('userID')!)).subscribe(() => {
            this.subscribed = false;
        });
    }
}
