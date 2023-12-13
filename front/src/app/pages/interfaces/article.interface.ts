import { Topic} from "./topic.interface";

export interface Article {
    id: number,
    content: string,
    title: string,
    created_at: string,
    user_id: string,
    topic_id: Topic,
}
