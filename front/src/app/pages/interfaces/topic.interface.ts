import {Article} from "./article.interface";

export interface Topic {
    id: number,
    title: string,
    content: string,
    theme_id: number,
    created_at: string,
    updated_at: string,
    articles: Article[]
}
