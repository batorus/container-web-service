
## Endpoints: 

### Container Controller:

GET /api/v1/containers 

POST /api/v1/containers 

GET /api/v1/containers/{id} 

PUT /api/v1/containers/{id} 

DELETE /api/v1/containers/{id} 

POST /api/v1/containers/savebatch 

### Item Controller:

GET /api/v1/containers/{containerId}/items 

POST /api/v1/containers/{containerId}/items 

PUT /api/v1/containers/{containerId}/items/{itemId} 

GET /api/v1/items/{itemId} 

DELETE /api/v1/items/{itemId} 

GET /api/v1/items/{itemId}/tags 

GET /api/v1/items/{itemId}/tags/{tagId} 

DELETE /api/v1/items/{itemId}/tags/{tagId} 

### Tag Controller:

GET /api/v1/tags 

POST /api/v1/tags

GET /api/v1/tags/{id}

PUT /api/v1/tags/{id}

DELETE /api/v1/tags/{id}
