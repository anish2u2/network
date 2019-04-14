package org.network;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.network.contracts.HttpNetwork;
import org.network.contracts.Reader;
import org.network.http.HttpClientFactory;
import org.workers.contracts.Story;
import org.workers.contracts.Task;
import org.workers.factory.ManagerFactory;

public class Main {

	static Boolean bool = new Boolean(false);

	public static void main(String args[]) throws Exception {
		HttpNetwork network = HttpClientFactory.getInstance(new URL(
				"https://converter.savefrom.net/joiner?id=p3HR9QDMj18&v=https%3A%2F%2Fredirector.googlevideo.com%2Fvideoplayback%3Finitcwndbps%3D880000%26c%3DWEB%26fvip%3D1%26pl%3D23%26aitags%3D133%252C134%252C135%252C136%252C137%252C160%252C242%252C243%252C244%252C247%252C248%252C278%252C394%252C395%252C396%252C397%252C398%26requiressl%3Dyes%26mime%3Dvideo%252Fwebm%26keepalive%3Dyes%26source%3Dyoutube%26sparams%3Daitags%252Cclen%252Cdur%252Cei%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cexpire%26itag%3D247%26expire%3D1555201529%26gir%3Dyes%26mm%3D31%252C29%26mn%3Dsn-nu5goxu-3p86%252Csn-4g5edn7y%26dur%3D172.166%26mt%3D1555179813%26mv%3Dm%26ms%3Dau%252Crdu%26lmt%3D1553250610552618%26clen%3D26518796%26ip%3D87.248.177.49%26key%3Dyt6%26ipbits%3D0%26txp%3D5531432%26id%3Do-ALm3bdy7XauvNoXHpKF0zHdLMgzkPYcYfidInDzWcFYK%26ei%3DmSmyXKfLJpe61gLq75CgBA%26signature%3D71A610196590B25644E9CEFC6B695CF6EB09D97B.0DF10E6575C0ED047902053B233BA7273196E9DD&a=https%3A%2F%2Fredirector.googlevideo.com%2Fvideoplayback%3Finitcwndbps%3D880000%26c%3DWEB%26fvip%3D1%26pl%3D23%26requiressl%3Dyes%26mime%3Daudio%252Fwebm%26keepalive%3Dyes%26source%3Dyoutube%26sparams%3Dclen%252Cdur%252Cei%252Cgir%252Cid%252Cinitcwndbps%252Cip%252Cipbits%252Citag%252Ckeepalive%252Clmt%252Cmime%252Cmm%252Cmn%252Cms%252Cmv%252Cpl%252Crequiressl%252Csource%252Cexpire%26itag%3D171%26expire%3D1555201529%26gir%3Dyes%26mm%3D31%252C29%26mn%3Dsn-nu5goxu-3p86%252Csn-4g5edn7y%26dur%3D172.162%26mt%3D1555179813%26mv%3Dm%26ms%3Dau%252Crdu%26lmt%3D1553254771335914%26clen%3D2870451%26ip%3D87.248.177.49%26key%3Dyt6%26ipbits%3D0%26txp%3D5511222%26id%3Do-ALm3bdy7XauvNoXHpKF0zHdLMgzkPYcYfidInDzWcFYK%26ei%3DmSmyXKfLJpe61gLq75CgBA%26signature%3D4B4E6EDFF0C9F2B18B1880D12294A7335BFE5318.C012BD954249C0BFEA4F4BFEF5A3BC66A20AB0AD&ts=1555180527&ip=157.40.73.156&sig=97e6423513a91f3170acb72d9fbe769c&t=Kalank+-+First+Class+-+Varun+D%2C+Alia+B%2C+Kiara+%26+Madhuri+-+Arijit+S+-+Pritam-Amitabh-Abhishek+Varman"));
		network.connect();
		final Reader reader = network.getReader();
		final Object lock = new Object();

		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024*8);
		ManagerFactory.getManager().assignStory(new Story() {

			public void setStoryId(Long storyId) {

			}

			public int progressReport() {

				return 0;
			}

			public Task getTask() {

				return new Task() {
					byte[] buffer = new byte[1024 * 8];

					public String getTaskName() {

						return "Reader";
					}

					public void executeTask() {
						try {
							int length = 0, counter = 0;
							System.out.println("reading started");
							while ((length = reader.read(buffer)) != -1) {
								synchronized (lock) {
									outputStream.write(buffer, 0, length);
									if (counter == 16) {
										lock.notify();
										counter = 0;
										Thread.sleep(10);
									}
								}

								counter++;
							}
							System.out.println("reading finished");
							bool = true;
							outputStream.close();
							synchronized (lock) {
								lock.notify();
							}
						} catch (Exception e) {

							e.printStackTrace();
						}

					}
				};
			}

			public String getStoryName() {
				// TODO Auto-generated method stub
				return "Youtube video reader";
			}

			public Long getStoryId() {

				return 128L;
			}

			public int getPriority() {
				// TODO Auto-generated method stub
				return 0;
			}

			public void closeStory() {
				// TODO Auto-generated method stub

			}
		});

		ManagerFactory.getManager().assignStory(new Story() {

			public void setStoryId(Long storyId) {
				// TODO Auto-generated method stub

			}

			public int progressReport() {
				// TODO Auto-generated method stub
				return 0;
			}

			public Task getTask() {
				// TODO Auto-generated method stub
				return new Task() {

					public String getTaskName() {
						// TODO Auto-generated method stub
						return "Writer";
					}

					public void executeTask() {
						try {
							FileOutputStream fileOutputStream = new FileOutputStream("D://fist class.mp4");
							byte[] data = null;
							System.out.println("Writing started..");
							while (!bool || outputStream.size() > 0) {
								synchronized (lock) {
									data = outputStream.toByteArray();
									outputStream.reset();
									lock.notify();
									Thread.sleep(10);
								}
								fileOutputStream.write(data);
								fileOutputStream.flush();
							}
							System.out.println("Writing finished..");
							fileOutputStream.close();
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				};
			}

			public String getStoryName() {
				// TODO Auto-generated method stub
				return null;
			}

			public Long getStoryId() {
				// TODO Auto-generated method stub
				return null;
			}

			public int getPriority() {
				// TODO Auto-generated method stub
				return 0;
			}

			public void closeStory() {
				// TODO Auto-generated method stub

			}
		});

	}

}
