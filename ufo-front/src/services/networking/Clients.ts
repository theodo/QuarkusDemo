import request from 'superagent';

// @ts-ignore
export const awsHttpdIp = window.KUB_HTTPD;
const backendBaseUrl = `http://${awsHttpdIp}:80`;

type Method = 'get' | 'post' | 'put' | 'patch' | 'delete';

class Client {
  baseUrl: string;
  agent: request.SuperAgentStatic;

  constructor(baseUrl: string) {
    this.baseUrl = baseUrl;

    this.agent = request.agent();
    // @ts-ignore
    this.agent.accept('application/json');
  }

  async request(method: Method, endpoint: string, data: object | null = null) {

    const url = /^https?:\/\//.test(endpoint) ? endpoint : `${this.baseUrl}${endpoint}`;
    let promise = this.agent[method](url);

    if (['post', 'put', 'patch'].includes(method) && data) {
      promise = promise.send(data);
    }

    const { body } = await promise;
    return body;
  }

  get(endpoint: string) {
    return this.request('get', endpoint);
  }

  post(endpoint: string, data: object) {
    return this.request('post', endpoint, data);
  }

  put(endpoint: string, data: object) {
    return this.request('put', endpoint, data);
  }

}

export const clients = new Client(backendBaseUrl);
export const ufoClient = new Client(backendBaseUrl);
