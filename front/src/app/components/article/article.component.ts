import {Component, OnInit, Input} from '@angular/core';
import {Article} from "../../pages/interfaces/article.interface";
import {Topic} from "../../pages/interfaces/topic.interface";
import {TopicService} from "../../pages/services/topic.service";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
    @Input() article: Article | undefined;

    private topic: Topic | undefined;
    private TopicsService: TopicService = new TopicService(this.httpClient);

    constructor(private httpClient: HttpClient) {
    }

    ngOnInit(): void {
/*        console.log(this.article?.topic_id);
        this.TopicsService.getTopicById(this.article?.topic_id).subscribe((TopicFound: Topic) => {
            this.topic = TopicFound;
            console.log(this.topic);
        });*/
    }
}
