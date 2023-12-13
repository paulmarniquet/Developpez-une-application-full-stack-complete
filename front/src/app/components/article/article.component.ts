import {Component, OnInit, Input} from '@angular/core';

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
    @Input() titre: string | undefined;
    @Input() date: string | undefined;
    @Input() auteur: string | undefined;
    @Input() contenu: string | undefined;

    constructor() {}

    ngOnInit(): void {
    }

}
