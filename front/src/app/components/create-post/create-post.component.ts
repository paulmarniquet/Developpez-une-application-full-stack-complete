import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Article} from "../../interfaces/article.interface";

@Component({
    selector: 'app-create-post',
    templateUrl: './create-post.component.html',
    styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

    protected article?: Article;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.article = history.state.articleData;
        });
        console.log(this.article);
    }

}
