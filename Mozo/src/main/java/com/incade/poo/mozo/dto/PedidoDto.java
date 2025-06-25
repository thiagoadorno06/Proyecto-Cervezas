package com.incade.poo.mozo.dto;

import java.util.List;

public record PedidoDto(Long id, Integer mesa, MozoDto mozo, String estado, Double total, List<ItemDto> items) {

}
