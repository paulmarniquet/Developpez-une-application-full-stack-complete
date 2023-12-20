import {Component, Input, OnInit} from '@angular/core';
import {Topic} from "../../interfaces/topic.interface";
import {Article} from "../../interfaces/article.interface";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit {

    @Input() topic?: Topic;

    constructor() { }

  ngOnInit(): void {
  }

}
