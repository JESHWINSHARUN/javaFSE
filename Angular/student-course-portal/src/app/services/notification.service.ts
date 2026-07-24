import { Injectable } from '@angular/core';

@Injectable()
export class NotificationService {
  private count = 0;
  increment(): number { return ++this.count; }
}
