package com.incade.poo.mozo.dto;

import java.util.List;

public record PedidoDto(Long id, Long mesa, MozoDto mozo, String estado, Double total, List<ItemDto> items) {

}
