export class Event {
  id: number | null;
  date: Date | null;
  title: String;

  constructor(event: Partial<Event> = {}) {
    this.id = event?.id || null;
    this.date = event?.date || null;
    this.title = event?.title || '';
  }
}
