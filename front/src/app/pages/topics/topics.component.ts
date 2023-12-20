import { Component, OnInit } from '@angular/core';
import {User} from "../../interfaces/user.interface";
import {Topic} from "../../interfaces/topic.interface";
import {TopicService} from "../../services/topic.service";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
    public user: User | undefined;
    public topics: Topic[] = [];

    constructor(private topicService: TopicService) {
    }

    ngOnInit(): void {
        this.topicService.getTopics().subscribe((topics: any) => {
            this.topics = topics;
        });
    }

}
