explanation
The environment of this system is for the use of a post office employee whose educational qualification is a diploma and does not specialize in computer knowledge, so the program must be implemented well. The primary menu options are as follows and in the implementation of each one, the corresponding data structure should be used:
1-Receiving the shipment: it simply takes the details of the shipment (name of the shipment, sender, recipient, and distance to the destination) and after creating a random tracking code (random) registers a 6-digit code. By registering each shipment, the shipment is placed in the received queue. .
2- Sending parcels: sending parcels is done with two priorities, the type of which must be specified:
• The first shipment in the queue • The closest destination: by designing a min heap based on the distance to the destination of each shipment, the shortest distance to the destination
specifies for the delivery agent.
After selecting the priority type, the relevant shipment will be displayed and will be sent if approved by the employee.
3- Registering the new status: after sending each shipment, its new status is registered manually. In this way, in this section, the tracking code of the shipment is entered first, and then the new status for the said shipment is entered. Each state is stored in the sent state stack. By entering the "delivered" status, a new status is registered for this shipment
can't
4-Tracking the shipment status: By entering the tracking code of each shipment, its previous and current statuses will be shown respectively.
5- Mailing archive: This section is related to the implementation of the linked list. There is a linked list for the post office to organize all the mails. After each consignment is delivered, the created consignment is saved in the linked list. The search to find the shipments is done on the tracking code on this linked list.
You must implement the class related to the shipment correctly and according to the mentioned features.
All the required building data of the project must be implemented by you
The only data structure allowed is the simple array. ArrayList, Vector, etc. are not allowed and you have to implement them yourself to use them.