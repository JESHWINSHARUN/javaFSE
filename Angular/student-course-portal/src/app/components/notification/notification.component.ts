import { Component } from '@angular/core';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification',
  standalone: true,
  providers: [NotificationService],
  template: `<div class="notification">Notifications: {{ count }}</div>`
})
export class NotificationComponent {
  count = 0;

  constructor(private notificationService: NotificationService) {
    this.count = this.notificationService.increment();
  }
}
