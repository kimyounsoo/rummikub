package cube;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;



public class CubeHandler extends Thread {
	private Socket socket;
	private List<CubeHandler> list;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public CubeHandler(Socket socket, List<CubeHandler> list) throws IOException {
		this.socket = socket;
		this.list = list;

		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		// 큐브게임으로부터 받는 쪽
		UserDTO dto = null;
		String id = null;

		while (true) {
			try {
				dto = (UserDTO) ois.readObject();

				if (dto.getCommand() == Info.JOIN) {
					// 모든 클라이언트에게(나 포함) 입장메세지를 보내기
					UserDTO sendDTO = new UserDTO();
					sendDTO.setCommand(Info.SEND);
					id = dto.getId();
					sendDTO.setMessage(id + "님 입장하였습니다");
					broadcast(sendDTO);

				} else if (dto.getCommand() == Info.EXIT) {
					UserDTO sendDTO = new UserDTO();

					// quit를 보낸 클라이언트에게 quit를 보내기(나에게)
					sendDTO.setCommand(Info.EXIT);
					oos.writeObject(sendDTO);
					oos.flush();
					
					ois.close();
					oos.close();
					socket.close();

					// 남은 클라이언트에게즈 퇴장메세지 보내기
					list.remove(this);

					sendDTO.setCommand(Info.SEND);
					sendDTO.setMessage(id + "님 퇴장하였습니다");
					
					broadcast(sendDTO);
				
					break;

				} else if (dto.getCommand() == Info.SEND) {
					UserDTO sendDTO = new UserDTO();
					sendDTO.setCommand(Info.SEND);
					sendDTO.setMessage("[" + id + "] " + dto.getMessage());
					broadcast(sendDTO);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // while
	}

	private void broadcast(UserDTO sendDTO) {
		for (CubeHandler handler : list) {
			try {
				handler.oos.writeObject(sendDTO);
				handler.oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // for
	}

}
